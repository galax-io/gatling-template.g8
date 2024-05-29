package $package$.$name;format="word"$

import scala.annotation.tailrec

object Utility {

  private val preps = Array("", "K", "M", "G", "T")

  private def humanise(sizeBytes: Long): String = {
    @tailrec
    def go(n: Long, divider: Long, offset: Int): String = {
      if (n < 1024L)
        s"\${(sizeBytes.toDouble / divider).round} \${preps(offset)}B"
      else
        go(n / 1024, divider * 1024, offset + 1)
    }

    go(sizeBytes, 1L, 0)

  }

  def debugMemoryAndOpts(): Unit = {
    sys.env
      .get("GATLING_JAVA_OPTS")
      .fold(println("GATLING_JAVA_OPTS not set"))(javaOpts => println(s"GATLING_JAVA_OPTS = \$javaOpts"))

    val heapSize     = Runtime.getRuntime.totalMemory()
    val heapMaxSize  = Runtime.getRuntime.maxMemory()
    val heapFreeSize = Runtime.getRuntime.freeMemory()
    val percentFree  = ((heapFreeSize.toDouble / heapSize) * 100).round

    println(
      s"Heap: \${humanise(heapSize)} | HeapMax: \${humanise(heapMaxSize)} | Free: \${humanise(heapFreeSize)}/\${humanise(heapSize)} [\$percentFree %]",
    )
  }

}
