package dao;

import java.util.List;

public interface ITask<E> {
    public List<E> getAll();
    public boolean addNew();
    public E searchOneById(int id);
}
