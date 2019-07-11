package monocle

class KotlinClass {
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

    fun getStringRes() = "12313123123" + stringFromJNI();

    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("monocle-lib")
        }
    }
}