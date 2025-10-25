package fi.ishtech.practice.bookapp.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import fi.ishtech.practice.bookapp.dto.BookDto;
import fi.ishtech.practice.bookapp.entity.Book;

/**
 * Mapper for {@link BookDto} to {@link Book} entity and vice-versa
 *
 * @author Muneer Ahmed Syed
 */
@Mapper(componentModel = "spring")
public interface BookMapper {

	/**
	 *
	 * @param entity {@link Book}
	 * @return {@link BookDto}
	 */
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "id", target = "id")
	@Mapping(source = "title", target = "title")
	@Mapping(source = "author", target = "author")
	@Mapping(source = "year", target = "year")
	@Mapping(source = "price", target = "price")
	BookDto toBriefDto(Book entity);

	//@IterableMapping
	@InheritConfiguration(name = "toBriefDto")
	List<BookDto> toBriefDto(List<Book> entities);

	/**
	 *
	 * @param dto    {@link BookDto}
	 * @param entity {@link Book}
	 * @return updated {@link Book} entity
	 */
	@InheritInverseConfiguration(name = "toBriefDto")
	Book toEntity(BookDto dto, @MappingTarget Book entity);

	/**
	 *
	 * @param dto {@link BookDto}
	 * @return new {@link Book} entity
	 */
	@InheritInverseConfiguration(name = "toBriefDto")
	@Mapping(target = "id", ignore = true)
	Book toNewEntity(BookDto dto);

}