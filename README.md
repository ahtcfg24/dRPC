# dRPC——gRPC+负载均衡的应用

大四随便做的毕业设计，组装下轮子而已，可以当做gRPC使用Demo，勿过度较真

server目录下是RPC服务端
client目录下是RPC客户端
web目录下是前端控制台，可以通过web发起普通HTTP请求到client，client再通过gRPC请求Server，最后把信息返回到Web上显示
