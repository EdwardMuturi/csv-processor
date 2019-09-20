// Generated code from Butter Knife. Do not modify!
package com.edward.csvprocessor.view;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.edward.csvprocessor.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ParserActivity_ViewBinding implements Unbinder {
  private ParserActivity target;

  private View view7f08004c;

  @UiThread
  public ParserActivity_ViewBinding(ParserActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ParserActivity_ViewBinding(final ParserActivity target, View source) {
    this.target = target;

    View view;
    target.tvFileContents = Utils.findRequiredViewAsType(source, R.id.tvDisplayData, "field 'tvFileContents'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnOpenFile, "method 'openFile'");
    view7f08004c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.openFile();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ParserActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvFileContents = null;

    view7f08004c.setOnClickListener(null);
    view7f08004c = null;
  }
}
