package fi.ishtech.practice.bookapp.entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_book",
		uniqueConstraints = @UniqueConstraint(name = "uk_book_title_author", columnNames = { "title", "author" }))
@Data
@NoArgsConstructor
public class Book implements Serializable {

	@Serial
	private static final long serialVersionUID = -7930169589351866235L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;

	@Column(nullable = false, length = 255)
	private String title;

	@Column(nullable = false, length = 255)
	private String author;

	@Column(nullable = false, precision = 4, columnDefinition = "SMALLINT")
	private Short year;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal price;

}