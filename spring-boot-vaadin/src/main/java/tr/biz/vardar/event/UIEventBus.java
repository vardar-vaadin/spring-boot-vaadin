package tr.biz.vardar.event;

import tr.biz.vardar.MyUI;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;

/**
 * A simple wrapper for Guava event bus. Defines static convenience methods for
 * relevant actions.
 */
public class UIEventBus implements SubscriberExceptionHandler {

    private final EventBus eventBus = new EventBus(this);

    public static void post(final Object event) {
        MyUI.getUIEventbus().eventBus.post(event);
    }

    public static void register(final Object object) {
        MyUI.getUIEventbus().eventBus.register(object);
    }

    public static void unregister(final Object object) {
        MyUI.getUIEventbus().eventBus.unregister(object);
    }

    @Override
    public final void handleException(final Throwable exception,
            final SubscriberExceptionContext context) {
        exception.printStackTrace();
    }
}
