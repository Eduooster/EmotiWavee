package org.example.emotiwave.domain.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Generated;

@Entity
@Table(
        name = "T_ANALISE_MUSICA"
)
public class AnaliseMusica {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String label;
    private BigDecimal score;
    private LocalDate analisado_em;
    private String polaridade;
    private String intensidade;


    public String getPolaridade() {
        return polaridade;
    }

    public void setPolaridade(String polaridade) {
        this.polaridade = polaridade;
    }

    public String getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(String intensidade) {
        this.intensidade = intensidade;
    }



    @OneToOne
    @JoinColumn(
            name = "musica_id",
            unique = true
    )
    private Musica musica;

    @Generated
    public AnaliseMusica() {
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getLabel() {
        return this.label;
    }

    @Generated
    public BigDecimal getScore() {
        return this.score;
    }

    @Generated
    public LocalDate getAnalisado_em() {
        return this.analisado_em;
    }

    @Generated
    public Musica getMusica() {
        return this.musica;
    }

    @Generated
    public void setId(final Long id) {
        this.id = id;
    }

    @Generated
    public void setLabel(final String label) {
        this.label = label;
    }

    @Generated
    public void setScore(final BigDecimal score) {
        this.score = score;
    }

    @Generated
    public void setAnalisado_em(final LocalDate analisado_em) {
        this.analisado_em = analisado_em;
    }

    @Generated
    public void setMusica(final Musica musica) {
        this.musica = musica;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof AnaliseMusica)) {
            return false;
        } else {
            AnaliseMusica other = (AnaliseMusica)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                Object this$label = this.getLabel();
                Object other$label = other.getLabel();
                if (this$label == null) {
                    if (other$label != null) {
                        return false;
                    }
                } else if (!this$label.equals(other$label)) {
                    return false;
                }

                Object this$score = this.getScore();
                Object other$score = other.getScore();
                if (this$score == null) {
                    if (other$score != null) {
                        return false;
                    }
                } else if (!this$score.equals(other$score)) {
                    return false;
                }

                Object this$analisado_em = this.getAnalisado_em();
                Object other$analisado_em = other.getAnalisado_em();
                if (this$analisado_em == null) {
                    if (other$analisado_em != null) {
                        return false;
                    }
                } else if (!this$analisado_em.equals(other$analisado_em)) {
                    return false;
                }

                Object this$musica = this.getMusica();
                Object other$musica = other.getMusica();
                if (this$musica == null) {
                    if (other$musica != null) {
                        return false;
                    }
                } else if (!this$musica.equals(other$musica)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof AnaliseMusica;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $label = this.getLabel();
        result = result * 59 + ($label == null ? 43 : $label.hashCode());
        Object $score = this.getScore();
        result = result * 59 + ($score == null ? 43 : $score.hashCode());
        Object $analisado_em = this.getAnalisado_em();
        result = result * 59 + ($analisado_em == null ? 43 : $analisado_em.hashCode());
        Object $musica = this.getMusica();
        result = result * 59 + ($musica == null ? 43 : $musica.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        Long var10000 = this.getId();
        return "AnaliseMusica(id=" + var10000 + ", label=" + this.getLabel() + ", score=" + String.valueOf(this.getScore()) + ", analisado_em=" + String.valueOf(this.getAnalisado_em()) + ", musica=" + String.valueOf(this.getMusica()) + ")";
    }
}

