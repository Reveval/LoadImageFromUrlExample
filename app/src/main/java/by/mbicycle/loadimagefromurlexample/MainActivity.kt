package by.mbicycle.loadimagefromurlexample

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)

        //Объявляем Executor для парсинга URL
        val executor = Executors.newSingleThreadExecutor()

        /*
            Когда executor парсит URL и получает изображение
            handler загрузит его для ImageView

         */
        val handler = Handler(Looper.getMainLooper())

        var image: Bitmap? = null

        executor.execute {
            //URL изображения
            val imageURL = "https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png"

            /*
                Пробуем получить изображение и опубликовать его для ImageView
                при помощи handler
             */

            try {
                val imageStream = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(imageStream)

                handler.post {
                    imageView.setImageBitmap(image)
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}