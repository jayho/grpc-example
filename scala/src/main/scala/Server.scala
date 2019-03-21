import io.grpc._
import com.example.protos.hello._
import scala.concurrent._

object Server extends App {
  val server = ServerBuilder
    .forPort(50051)
    .addService(
      GreeterGrpc.bindService(new GreeterImpl, ExecutionContext.global))
    .build
    .start

  sys.addShutdownHook {
    server.shutdown()
  }
  server.awaitTermination()

  private class GreeterImpl extends GreeterGrpc.Greeter {
    override def sayHello(req: HelloRequest) = {
      println(s"Received $req")
      val reply = HelloReply(message = "Hello " + req.name)
      Future.successful(reply)
    }
  }
}
