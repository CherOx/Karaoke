package com.mvp4g.client;

import com.mvp4g.client.history.PlaceService;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.mvp4g.client.presenter.PresenterInterface;
import com.mvp4g.client.event.EventBus;
import com.mvp4g.client.Mvp4gException;
import com.mvp4g.client.history.HistoryConverter;
import com.mvp4g.client.Mvp4gEventPasser;
import com.mvp4g.client.Mvp4gModule;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.mvp4g.client.event.BaseEventBus;
import com.mvp4g.client.event.EventFilter;
import com.mvp4g.client.event.EventHandlerInterface;
import java.util.List;
import com.mvp4g.client.history.NavigationEventCommand;
import com.mvp4g.client.history.NavigationConfirmationInterface;
import com.google.gwt.core.client.RunAsyncCallback;
import com.mvp4g.client.Mvp4gRunAsync;
import com.google.gwt.user.client.Command;

public class Mvp4gModuleImpl implements Mvp4gModule {
    
    private abstract class AbstractEventBus extends com.mvp4g.client.event.BaseEventBus implements karaoke.client.MainEventBus{}
    
    @GinModules({com.mvp4g.client.DefaultMvp4gGinModule.class})
    public interface com_mvp4g_client_Mvp4gModuleGinjector extends Ginjector {
      karaoke.client.presenter.RootPresenter getkaraoke_client_presenter_RootPresenter();
      karaoke.client.presenter.WorkPresenter getkaraoke_client_presenter_WorkPresenter();
      karaoke.client.view.RootView getkaraoke_client_presenter_RootPresenterView();
      karaoke.client.view.WorkView getkaraoke_client_presenter_WorkPresenterView();
      com.mvp4g.client.history.PlaceService getcom_mvp4g_client_history_PlaceService();
    }
    private Object startView = null;
    private PresenterInterface startPresenter = null;
    protected AbstractEventBus eventBus = null;
    protected com_mvp4g_client_Mvp4gModuleGinjector injector = null;
    protected com.mvp4g.client.Mvp4gModule itself = this;
    private PlaceService placeService = null;
    public void setParentModule(com.mvp4g.client.Mvp4gModule module){}
    public void loadChildModule(String childModuleClassName, String eventName, boolean passive, Mvp4gEventPasser passer){
    }
    public void addConverter(String historyName, HistoryConverter<?> hc){
      placeService.addConverter(historyName, hc);
    }
    public void clearHistory(){
      placeService.clearHistory();
    }
    public String place(String token, String form, boolean onlyToken){
      return placeService.place( token, form, onlyToken );
    }
    public void dispatchHistoryEvent(String eventType, final Mvp4gEventPasser passer){
      int index = eventType.indexOf(PlaceService.MODULE_SEPARATOR);
      if(index > -1){
        String moduleHistoryName = eventType.substring(0, index);
        String nextToken = eventType.substring(index + 1);
        Mvp4gEventPasser nextPasser = new Mvp4gEventPasser(nextToken) {
          public void pass(Mvp4gModule module) {
            module.dispatchHistoryEvent((String) eventObjects[0], passer);
          }
        };
        passer.setEventObject(false);
        passer.pass(this);
      }else{
        passer.pass(this);
      }
    }
    public void sendInitEvent(){
    }
    public void sendNotFoundEvent(){
    }
    
    public void onForward(){
    }
    
    public void createAndStartModule(){
      injector = GWT.create( com_mvp4g_client_Mvp4gModuleGinjector.class );
      final karaoke.client.view.RootView karaoke_client_presenter_RootPresenterView = injector.getkaraoke_client_presenter_RootPresenterView();
      final karaoke.client.view.WorkView karaoke_client_presenter_WorkPresenterView = injector.getkaraoke_client_presenter_WorkPresenterView();
      
      
      
      placeService = injector.getcom_mvp4g_client_history_PlaceService();
      
      final karaoke.client.presenter.RootPresenter karaoke_client_presenter_RootPresenter = injector.getkaraoke_client_presenter_RootPresenter();
      karaoke_client_presenter_RootPresenter.setView(karaoke_client_presenter_RootPresenterView);
      final karaoke.client.presenter.WorkPresenter karaoke_client_presenter_WorkPresenter = injector.getkaraoke_client_presenter_WorkPresenter();
      karaoke_client_presenter_WorkPresenter.setView(karaoke_client_presenter_WorkPresenterView);
      
      
      eventBus = new AbstractEventBus(){
        protected <T extends EventHandlerInterface<?>> T createHandler( Class<T> handlerClass ){
        return null;
        }
        public void displayMessage(java.lang.String attr0){
          if (karaoke_client_presenter_RootPresenter.isActivated(false, "displayMessage", new Object[]{attr0})){
            karaoke_client_presenter_RootPresenter.onDisplayMessage(attr0);
          }
        }
        public void start(){
          if (karaoke_client_presenter_RootPresenter.isActivated(false, "start")){
            karaoke_client_presenter_RootPresenter.onStart();
          }
          if (karaoke_client_presenter_WorkPresenter.isActivated(false, "start")){
            karaoke_client_presenter_WorkPresenter.onStart();
          }
        }
        public void changeBody(com.google.gwt.user.client.ui.Widget attr0){
          if (karaoke_client_presenter_RootPresenter.isActivated(false, "changeBody", new Object[]{attr0})){
            karaoke_client_presenter_RootPresenter.onChangeBody(attr0);
          }
        }
        public void setNavigationConfirmation( NavigationConfirmationInterface navigationConfirmation ) {
          placeService.setNavigationConfirmation(navigationConfirmation);
        }
        public void confirmNavigation(NavigationEventCommand event){
          placeService.confirmEvent(event);
        }
        public void setApplicationHistoryStored( boolean historyStored ){
          placeService.setEnabled(historyStored);
        }
        };
        
        karaoke_client_presenter_RootPresenter.setEventBus(eventBus);
        karaoke_client_presenter_WorkPresenter.setEventBus(eventBus);
        placeService.setModule(itself);
        
        this.startPresenter = karaoke_client_presenter_RootPresenter;
        this.startView = startPresenter.getView();
        eventBus.start();
      }
      public Object getStartView(){
        if (startPresenter != null) {
          startPresenter.setActivated(true);
          startPresenter.isActivated(false, null);
        }return startView;
      }
      
      public EventBus getEventBus(){
        return eventBus;
      }
    }
