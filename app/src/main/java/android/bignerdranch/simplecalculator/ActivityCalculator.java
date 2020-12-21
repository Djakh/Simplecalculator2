package android.bignerdranch.simplecalculator;

import androidx.fragment.app.Fragment;

public class ActivityCalculator extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new FragmentCalculator().newFragment();
    }
}
