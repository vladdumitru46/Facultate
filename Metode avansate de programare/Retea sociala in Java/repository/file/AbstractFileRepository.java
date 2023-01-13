package com.example.reteasocialagui.example.repository.file;

import com.example.reteasocialagui.example.domain.Entity;
import com.example.reteasocialagui.example.domain.validators.Validator;
import com.example.reteasocialagui.example.repository.memory.InMemoryRepository;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public abstract class AbstractFileRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID, E> {
    String fileName;

    public AbstractFileRepository(String fileName, Validator<E> validator) {
        super(validator);
        this.fileName = fileName;
        loadData();
    }

    private void loadData() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                List<String> attr = Arrays.asList(line.split(","));
                E e = extractEntity(attr);
                ID id = e.getId();
                super.save(id, e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * extract entity  - template method design pattern
     * creates an entity of type E having a specified list of @code attributes
     *
     * @param attributes
     * @return an entity of type E
     */
    public abstract E extractEntity(List<String> attributes);

    protected abstract String createEntityAsString(E entity);

    @Override
    public E save(ID id, E entity) {
        E e = super.save(id, entity);
        if (e == null) {
            for (Map.Entry<ID, E> entry : super.get_all().entrySet()) {
                writeToFile(entry.getValue());
            }
        }
        return e;
    }

    @Override
    public E delete(ID id) {
        E e = super.delete(id);
        for (Map.Entry<ID, E> entry : super.get_all().entrySet()) {
            writeToFile(entry.getValue());
        }
        return e;
    }

    public E update(E entity) {
        E user = super.update(entity);
        for (Map.Entry<ID, E> entry : super.get_all().entrySet()) {
            writeToFile(entry.getValue());
        }
        return user;
    }

    protected void writeToFile(E entity) {
        try (BufferedWriter bW = new BufferedWriter(new FileWriter(fileName, true))) {
            bW.write(createEntityAsString(entity));
            bW.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

