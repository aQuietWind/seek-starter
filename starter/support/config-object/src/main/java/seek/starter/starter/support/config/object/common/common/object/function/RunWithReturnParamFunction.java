package seek.starter.starter.support.config.object.common.common.object.function;

@FunctionalInterface
public interface RunWithReturnParamFunction<T,F> {
    T run(F param);
}
