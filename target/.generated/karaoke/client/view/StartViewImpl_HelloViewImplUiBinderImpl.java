package karaoke.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.uibinder.client.UiBinderUtil;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiBinderUtil;
import com.google.gwt.user.client.ui.Widget;

public class StartViewImpl_HelloViewImplUiBinderImpl implements UiBinder<com.google.gwt.user.client.ui.Widget, karaoke.client.view.StartViewImpl>, karaoke.client.view.StartViewImpl.HelloViewImplUiBinder {

  interface Template extends SafeHtmlTemplates {
    @Template("Hello, <span class='{0}' id='{1}'></span> <a href='#Start'>Say good-bye</a>")
    SafeHtml html1(String arg0, String arg1);
     
  }

  Template template = GWT.create(Template.class);


  public com.google.gwt.user.client.ui.Widget createAndBindUi(final karaoke.client.view.StartViewImpl owner) {


    return new Widgets(owner).get_f_HTMLPanel1();
  }

  /**
   * Encapsulates the access to all inner widgets
   */
  class Widgets {
    private final karaoke.client.view.StartViewImpl owner;


    public Widgets(final karaoke.client.view.StartViewImpl owner) {
      this.owner = owner;
      build_style();  // generated css resource must be always created. Type: GENERATED_CSS. Precedence: 1
      build_domId0();  // more than one getter call detected. Type: DOM_ID_HOLDER, precedence: 2
    }

    SafeHtml template_html1() {
      return template.html1("" + get_style().important() + "", get_domId0());
    }

    /**
     * Getter for clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay called 1 times. Type: GENERATED_BUNDLE. Build precedence: 1.
     */
    private karaoke.client.view.StartViewImpl_HelloViewImplUiBinderImpl_GenBundle get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      return build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay();
    }
    private karaoke.client.view.StartViewImpl_HelloViewImplUiBinderImpl_GenBundle build_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay() {
      // Creation section.
      final karaoke.client.view.StartViewImpl_HelloViewImplUiBinderImpl_GenBundle clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay = (karaoke.client.view.StartViewImpl_HelloViewImplUiBinderImpl_GenBundle) GWT.create(karaoke.client.view.StartViewImpl_HelloViewImplUiBinderImpl_GenBundle.class);
      // Setup section.


      return clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay;
    }

    /**
     * Getter for style called 1 times. Type: GENERATED_CSS. Build precedence: 1.
     */
    private karaoke.client.view.StartViewImpl_HelloViewImplUiBinderImpl_GenCss_style get_style() {
      return build_style();
    }
    private karaoke.client.view.StartViewImpl_HelloViewImplUiBinderImpl_GenCss_style build_style() {
      // Creation section.
      final karaoke.client.view.StartViewImpl_HelloViewImplUiBinderImpl_GenCss_style style = get_clientBundleFieldNameUnlikelyToCollideWithUserSpecifiedFieldOkay().style();
      // Setup section.
      style.ensureInjected();


      return style;
    }

    /**
     * Getter for f_HTMLPanel1 called 1 times. Type: DEFAULT. Build precedence: 1.
     */
    private com.google.gwt.user.client.ui.HTMLPanel get_f_HTMLPanel1() {
      return build_f_HTMLPanel1();
    }
    private com.google.gwt.user.client.ui.HTMLPanel build_f_HTMLPanel1() {
      // Creation section.
      final com.google.gwt.user.client.ui.HTMLPanel f_HTMLPanel1 = new com.google.gwt.user.client.ui.HTMLPanel(template_html1().asString());
      // Setup section.

      // Attach section.
      UiBinderUtil.TempAttachment attachRecord0 = UiBinderUtil.attachToDom(f_HTMLPanel1.getElement());
      get_nameSpan();

      // Detach section.
      attachRecord0.detach();

      return f_HTMLPanel1;
    }

    /**
     * Getter for nameSpan called 1 times. Type: DEFAULT. Build precedence: 2.
     */
    private com.google.gwt.dom.client.SpanElement get_nameSpan() {
      return build_nameSpan();
    }
    private com.google.gwt.dom.client.SpanElement build_nameSpan() {
      // Creation section.
      final com.google.gwt.dom.client.SpanElement nameSpan = new com.google.gwt.uibinder.client.LazyDomElement(get_domId0()).get().cast();
      // Setup section.


      owner.nameSpan = nameSpan;

      return nameSpan;
    }

    /**
     * Getter for domId0 called 2 times. Type: DOM_ID_HOLDER. Build precedence: 2.
     */
    private java.lang.String domId0;
    private java.lang.String get_domId0() {
      return domId0;
    }
    private java.lang.String build_domId0() {
      // Creation section.
      domId0 = com.google.gwt.dom.client.Document.get().createUniqueId();
      // Setup section.


      return domId0;
    }
  }
}
