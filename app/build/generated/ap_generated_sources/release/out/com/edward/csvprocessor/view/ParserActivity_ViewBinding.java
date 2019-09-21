// Generated code from Butter Knife. Do not modify!
package com.edward.csvprocessor.view;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.edward.csvprocessor.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ParserActivity_ViewBinding implements Unbinder {
  private ParserActivity target;

  private View view7f080048;

  @UiThread
  public ParserActivity_ViewBinding(ParserActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ParserActivity_ViewBinding(final ParserActivity target, View source) {
    this.target = target;

    View view;
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.rvData, "field 'recyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.btnOpenFile, "method 'openFile'");
    view7f080048 = view;
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

    target.recyclerView = null;

    view7f080048.setOnClickListener(null);
    view7f080048 = null;
  }
}
