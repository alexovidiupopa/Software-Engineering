package ro.ubb.project.web.converter;

public interface Converter<Model, Dto> {
    Model dtoToModel(Dto dto);

    Dto modelToDto(Model model);
}
