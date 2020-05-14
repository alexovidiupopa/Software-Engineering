package ro.ubb.project.web.converter;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class AbstractConverter<Model, Dto> implements Converter<Model, Dto> {

    public Collection<Dto> convertModelsToDtos(Collection<Model> models) {
        return models.stream()
                .map(this::modelToDto)
                .collect(Collectors.toList());
    }

    public Collection<Model> convertDtosToModels(Collection<Dto> dtos) {
        return dtos.stream()
                .map(this::dtoToModel)
                .collect(Collectors.toList());
    }
}
