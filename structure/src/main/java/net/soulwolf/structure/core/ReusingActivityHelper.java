package net.soulwolf.structure.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import net.soulwolf.structure.core.fragment.AbsFragment;


public class ReusingActivityHelper {

    public static final String SINGLE_FRAGMENT_ACTIVITY_START_ME_PARAM = "SINGLE_FRAGMENT_ACTIVITY_START_ME_PARAM";

    ReusingActivity mActivity;

    ReusingActivityHelper(ReusingActivity activity) {
        mActivity = activity;
    }

    /**
     * add ragment
     */
    AbsFragment ensureFragment(FragmentParameter param) {
      return  addFragmentByTag(param);
    }

    /**
     * addFragmentByTag
     * 
     * @return
     */
    private AbsFragment addFragmentByTag(FragmentParameter parameter) {
        FragmentManager fm = mActivity.getSupportFragmentManager();
        AbsFragment fragment = (AbsFragment) fm.findFragmentByTag(parameter.getTag());
        if (fragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            fragment = (AbsFragment) Fragment.instantiate(mActivity, parameter.getFragmentClass().getName(), parameter.getParams());
            ft.add(android.R.id.content,fragment, parameter.getTag());
            ft.commit();
        } else if (fragment.isDetached()) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.attach(fragment);
            ft.commit();
        }
        return fragment;
    }

    static boolean isSingleFragmentIntent(Activity activity) {
        FragmentParameter param = activity.getIntent().getParcelableExtra(
                SINGLE_FRAGMENT_ACTIVITY_START_ME_PARAM);
        return param != null;
    }

    public static IntentBuilder builder(Context context) {
        IntentBuilder b = new IntentBuilder();
        b.create(context, ReusingActivity.class);
        return b;
    }

    public static class IntentBuilder {

        private FragmentParameter mParams;

        private Intent intent;

        public IntentBuilder create(Context context,
                Class<? extends Activity> clazz) {
            intent = new Intent(context, clazz);
            return this;
        }

        public IntentBuilder setFragmentParameter(FragmentParameter parameter) {
            this.mParams = parameter;
            return this;
        }


        public Intent build() {
            intent.putExtra(SINGLE_FRAGMENT_ACTIVITY_START_ME_PARAM, mParams);
            if(mParams != null){
                intent.addFlags(mParams.getFlags());
            }
            return intent;
        }
    }
}
