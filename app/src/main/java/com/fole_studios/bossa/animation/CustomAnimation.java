package com.fole_studios.bossa.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class CustomAnimation
{
    public static void transDownInvisible(View view)
    {
        view.animate().translationY(view.getHeight()).alpha(0.0f).setDuration(300).setListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
                view.setVisibility(View.GONE);
            }
        });
    }

    public static void transUpVisible(View view)
    {
        view.setVisibility(View.VISIBLE);
        view.animate().translationY(0).alpha(1.0f).setDuration(300).setListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                super.onAnimationEnd(animation);
                view.setVisibility(View.VISIBLE);
            }
        });
    }

    public static void fadeOutInvisible(View view)
    {
        view.animate().alpha(0.0f).setDuration(300).setListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    public static void fadeInVisible(View view)
    {
        view.setVisibility(View.VISIBLE);
        view.animate().alpha(1.0f).setDuration(3000).setListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                super.onAnimationEnd(animation);
                view.setVisibility(View.VISIBLE);
            }
        });
    }
}
