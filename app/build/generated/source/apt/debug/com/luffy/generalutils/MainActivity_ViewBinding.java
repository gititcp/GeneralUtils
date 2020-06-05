// Generated code from Butter Knife. Do not modify!
package com.luffy.generalutils;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view7f070050;

  private View view7f070051;

  private View view7f070052;

  private View view7f070053;

  private View view7f070054;

  private View view7f070055;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.scrollView = Utils.findRequiredViewAsType(source, R.id.scrollView, "field 'scrollView'", ScrollView.class);
    view = Utils.findRequiredView(source, R.id.item_1, "field 'item1' and method 'onViewClicked'");
    target.item1 = Utils.castView(view, R.id.item_1, "field 'item1'", LinearLayout.class);
    view7f070050 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_2, "field 'item2' and method 'onViewClicked'");
    target.item2 = Utils.castView(view, R.id.item_2, "field 'item2'", LinearLayout.class);
    view7f070051 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_3, "field 'item3' and method 'onViewClicked'");
    target.item3 = Utils.castView(view, R.id.item_3, "field 'item3'", LinearLayout.class);
    view7f070052 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_4, "field 'item4' and method 'onViewClicked'");
    target.item4 = Utils.castView(view, R.id.item_4, "field 'item4'", LinearLayout.class);
    view7f070053 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.imageView4 = Utils.findRequiredViewAsType(source, R.id.imageView_4, "field 'imageView4'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.item_5, "field 'item5' and method 'onViewClicked'");
    target.item5 = Utils.castView(view, R.id.item_5, "field 'item5'", LinearLayout.class);
    view7f070054 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.item_6, "field 'item6' and method 'onViewClicked'");
    target.item6 = Utils.castView(view, R.id.item_6, "field 'item6'", LinearLayout.class);
    view7f070055 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.scrollView = null;
    target.item1 = null;
    target.item2 = null;
    target.item3 = null;
    target.item4 = null;
    target.imageView4 = null;
    target.item5 = null;
    target.item6 = null;

    view7f070050.setOnClickListener(null);
    view7f070050 = null;
    view7f070051.setOnClickListener(null);
    view7f070051 = null;
    view7f070052.setOnClickListener(null);
    view7f070052 = null;
    view7f070053.setOnClickListener(null);
    view7f070053 = null;
    view7f070054.setOnClickListener(null);
    view7f070054 = null;
    view7f070055.setOnClickListener(null);
    view7f070055 = null;
  }
}
