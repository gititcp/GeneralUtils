// Generated code from Butter Knife. Do not modify!
package com.luffy.generalutils;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AppActivity_ViewBinding implements Unbinder {
  private AppActivity target;

  private View view7f070023;

  @UiThread
  public AppActivity_ViewBinding(AppActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AppActivity_ViewBinding(final AppActivity target, View source) {
    this.target = target;

    View view;
    target.edit = Utils.findRequiredViewAsType(source, R.id.edit, "field 'edit'", EditText.class);
    target.txtName = Utils.findRequiredViewAsType(source, R.id.txt_name, "field 'txtName'", TextView.class);
    target.imgIcon = Utils.findRequiredViewAsType(source, R.id.img_icon, "field 'imgIcon'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.btn_get, "method 'onViewClicked'");
    view7f070023 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AppActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edit = null;
    target.txtName = null;
    target.imgIcon = null;

    view7f070023.setOnClickListener(null);
    view7f070023 = null;
  }
}
