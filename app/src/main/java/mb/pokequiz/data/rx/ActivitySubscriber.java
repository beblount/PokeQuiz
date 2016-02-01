package mb.pokequiz.data.rx;

import java.lang.ref.SoftReference;

import mb.pokequiz.ui.BaseActivity;
import mb.pokequiz.util.Logg;
import rx.Subscriber;

public abstract class ActivitySubscriber<T> extends Subscriber<T> {

    private SoftReference<BaseActivity> activityBaseSoftReference;

    public ActivitySubscriber(BaseActivity baseActivity) {
        super();
        activityBaseSoftReference = new SoftReference<>(baseActivity);
        baseActivity.addSubscription(this);
    }

    @Override
    public void onError(Throwable e) {
        Logg.log(e);
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onCompleted() {
    }

    private void removeSelf() {
        BaseActivity baseActivity = activityBaseSoftReference.get();
        if (baseActivity != null) {
            baseActivity.removeSubscription(this);
        }
    }
}
