public class HelloWorld {
    public native void hello(String name);

    public HelloWorld() {

    }
    static {
        System.loadLibrary("lib/HelloWorld");
    }

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.hello("emaster");
    }
}
