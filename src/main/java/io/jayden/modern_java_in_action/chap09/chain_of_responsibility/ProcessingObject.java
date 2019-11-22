package io.jayden.modern_java_in_action.chap09.chain_of_responsibility;

public abstract class ProcessingObject<T> {

    protected ProcessingObject<T> successor;

    public void setSuccessor(ProcessingObject<T> successor) {
        this.successor = successor;
    }

    public T handle(T input) {
        T r = handleWork(input);
        if(this.successor != null){
            return this.successor.handle(r);
        }

        return r;
    }

    abstract protected T handleWork(T input);
}
