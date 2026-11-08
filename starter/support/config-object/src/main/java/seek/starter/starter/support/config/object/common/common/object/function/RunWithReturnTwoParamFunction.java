package seek.starter.starter.support.config.object.common.common.object.function;

@FunctionalInterface
public interface RunWithReturnTwoParamFunction<R,P1,P2> {
    R run(P1 param,P2 param2);
}
