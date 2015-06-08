/**
 * <pre>
 * Copyright 2014-2019 Soulwolf AppStructure
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </pre>
 */
package net.soulwolf.structure.core.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;

import net.soulwolf.structure.AppStructure;
import net.soulwolf.structure.core.FragmentParameter;
import net.soulwolf.structure.core.ReusingActivityHelper;
import net.soulwolf.structure.core.inter.FragmentJumpHandler;
import net.soulwolf.structure.core.inter.FragmentKeyEvent;

/**
 * author : Soulwolf Create by 2015/6/8 11:01
 * email  : ToakerQin@gmail.com.
 */
public class AbsFragment extends Fragment implements FragmentKeyEvent,FragmentJumpHandler{

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void jumpFragment(FragmentParameter parameter) {
        Intent intent ;
        intent = ReusingActivityHelper.builder(getContext()).setFragmentParameter(parameter).build();
        if(getActivity() == null){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        getContext().startActivity(intent);
    }

    @Override
    public void jumpFragment(Class<AbsFragment> fragmentClass) {
        jumpFragment(fragmentClass,null);
    }

    @Override
    public void jumpFragment(Class<AbsFragment> fragmentClass, Bundle args) {
        jumpFragment(new FragmentParameter(fragmentClass,args));
    }

    public Context getContext(){
        if(getActivity() == null){
            return AppStructure.getInstance().getContext();
        }
        return getActivity();
    }
}
