package javafeff.ca;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class W2A extends Activity
{
    AnimationDrawable androidAnimation; //describe frame by frame animations in which current drawable is replaced by next

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main); //establish user interface using main.xml
        //R is class name generated when app is built
        //layout is class nested in R
        //main is an integer constant in layout class


        ImageView androidImage = (ImageView) findViewById(R.id.android); //let app animate sequence of drawables
        //finds imageview element in main.xml


        androidImage.setBackgroundResource(R.drawable.android_animate);
        //identifies android_animate.xml
        //setbackground resource links androidImage to the sequence of images in the xml


        androidAnimation = (AnimationDrawable) androidImage.getBackground();
        //get imageview animationdrawable
        //Returns the animationDrawable for the given imageview


        final Button btnAnimate = (Button) findViewById(R.id.animate);
        //create animate button, obtains info from main.xml using findviewbyid


        View.OnClickListener ocl;
        ocl = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                androidAnimation.stop();
                androidAnimation.start();
            }
        };
        btnAnimate.setOnClickListener(ocl);
        //invoked when user clicks the button
    }
}