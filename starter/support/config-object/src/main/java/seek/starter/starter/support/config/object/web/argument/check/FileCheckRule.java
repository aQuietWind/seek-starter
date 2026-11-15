package seek.starter.starter.support.config.object.web.argument.check;



public abstract class FileCheckRule<T> implements CheckRule<T>{
    String[] types;
    long maxMb;

    public abstract boolean check(T arg);
}
