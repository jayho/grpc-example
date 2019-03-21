import io.grpc._
import com.example.protos.hello._

object Client extends App {
  val channel =
    ManagedChannelBuilder
      .forAddress("localhost", 50051)
      .usePlaintext(true)
      .build
  val request = HelloRequest(name = "World")
  val blockingStub = GreeterGrpc.blockingStub(channel)
  val reply: HelloReply = blockingStub.sayHello(request)
  println(reply)
}
