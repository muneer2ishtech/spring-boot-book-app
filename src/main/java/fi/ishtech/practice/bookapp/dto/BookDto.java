package fi.ishtech.practice.bookapp.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import fi.ishtech.common.validation.MaxCurrentYear;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Book
 *
 * @author Muneer Ahmed Syed
 */
@Data
@NoArgsConstructor
public class BookDto implements Serializable {

	@Serial
	private static final long serialVersionUID = 607043909100998314L;

	private Long id;

	@NotBlank
	private String title;

	@NotBlank
	private String author;

	@Min(1900)
	@MaxCurrentYear
	private Short year;

	@Positive
	private BigDecimal price;

}