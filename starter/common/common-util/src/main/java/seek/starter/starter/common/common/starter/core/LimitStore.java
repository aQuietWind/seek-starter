package seek.starter.starter.common.common.starter.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import seek.starter.starter.common.common.starter.support.Container;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class LimitStore <T>{
    private int currentSize=0;
    private int maxSize;
    private Container<T>[] containers;
    private int currentVersion;
    private int currentIndex;

    public LimitStore(int maxSize) {
        this.maxSize = maxSize;
        this.containers =new Container[maxSize];
    }

    public synchronized void init(Container<T>[] originContainers){
        for (Container<T> originContainer:originContainers)putOne(originContainer);
    }

    //通常情况下,一定满足Version递增条件,且数组长度最好还是大些
    public synchronized void putOne(Container<T> container){
        int index=getIndex(container.getVersion());
        boolean originIsNull=containers[index]==null;
        //判断数据新鲜度
        if (originIsNull||containers[index].getVersion()<container.getVersion()) {
            containers[index]=container;
        }else return;
        //尺寸,索引更新和版本号更新
        if (originIsNull)currentSize++;
        //保证永远更新为最新的数据
        if (currentVersion<container.getVersion()){
            currentVersion=container.getVersion();
            currentIndex=index;
        }
    }

    public T getCurrent(){
        return containers[currentIndex].getData();
    }

    public T getByVersion(int version){
        if (containers[getIndex(version)].getVersion()==version)return containers[getIndex(version)].getData();
        return null;
    }

    //重构
    public synchronized LimitStore<T> rebuild(int maxSize){
        LimitStore<T> newStore=new LimitStore<>(maxSize);
        //存放旧数据,性能没有优化,但是简单易维护
        for (Container<T> originContainer:containers)newStore.putOne(originContainer);
        return newStore;
    }



    public int getIndex(int version){
        return version%maxSize;
    }


}
