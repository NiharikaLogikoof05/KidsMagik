package com.logikoof.ecom.base;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.logikoof.ecom.R;


/**
 * Created by Dhananjay on 06/02/2019.
 */
public class CustomFragmentManager {

    public static void replaceFragment(FragmentManager fragmentManager, Fragment currentFragment, Fragment nextFragment,
                                       @Nullable String fragmentID){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }
        fragmentTransaction.replace(R.id.layout_container, nextFragment, fragmentID);
        fragmentTransaction.addToBackStack(fragmentID);
        fragmentTransaction.commit();
    }

    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentManager manager = fragmentManager;
        boolean fragmentPopped=false;
        try {
            fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
        }
        catch (Exception ex){

        }
        if (!fragmentPopped) { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.layout_container, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, boolean addToBackStack) {
        String backStateName = fragment.getClass().getName();
        FragmentManager manager = fragmentManager;
      //  boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
       // if (!fragmentPopped)
        { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.layout_container, fragment);
            if (addToBackStack) {
                ft.addToBackStack(backStateName);
            }
          //  ft.commit();
            ft.commitAllowingStateLoss();
        }
    }
    public static void removeFragment(FragmentManager fragmentManager, Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentManager manager = fragmentManager;
        fragmentManager.beginTransaction().remove(fragmentManager.findFragmentById(R.id.layout_container)).commit();
            FragmentTransaction ft = manager.beginTransaction();


    }

    public static void addFragment(FragmentManager fragmentManager, final Fragment fragment) {
        //final String backStateName = fragment.getClass().getName();
        final FragmentManager manager = fragmentManager;

        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.layout_container, fragment);
        //ft.addToBackStack(backStateName);
        ft.commit();
    }
}
