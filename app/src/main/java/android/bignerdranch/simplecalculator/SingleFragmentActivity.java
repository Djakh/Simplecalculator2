package android.bignerdranch.simplecalculator;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment ();

    @LayoutRes
    protected int getLayoutResId () {
        return R.layout.activity_fragment;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragmnet_container);

        if(fragment == null) {

         fragment = createFragment();
         fm.beginTransaction().
                 add( R.id.fragmnet_container, fragment).commit();



        }
    }
}
