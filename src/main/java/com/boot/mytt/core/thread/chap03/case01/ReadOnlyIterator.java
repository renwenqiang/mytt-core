package com.boot.mytt.core.thread.chap03.case01;

import java.util.Iterator;

/**
 * @author renwq
 * @date 2021/2/27 下午3:24
 */
public class ReadOnlyIterator<E> implements Iterator<E> {

    private final Iterator<E> delegate;

    private ReadOnlyIterator(Iterator<E> iterator) {
        this.delegate = iterator;
    }

    public static <E> Iterator<E> with(Iterator<E> iterator) {
        return new ReadOnlyIterator<E>(iterator);
    }

    @Override
    public boolean hasNext() {
        return delegate.hasNext();
    }

    @Override
    public E next() {
        return delegate.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
