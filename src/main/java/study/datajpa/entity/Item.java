package study.datajpa.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item implements Persistable<String> {

    /**
     * JPA 식별자 생성 전략 @GenerateValue 사용안하고 직접 할당할 경우
     * 이미 식별자 값이 있는 상태로 save하면 merge()가 호출된다.
     * merge()는 우선 DB를 호출해서 값을 확인하고, DB에 값이 없으면 새로운 엔티티로 인지하므로
     * Persistable를 사용해서 새로운 엔티티 확인 여부를 직접 구현하는게 효과적이다.
     */
    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdDate; //이 필드로 새로운 엔티티 여부를 편리하게 확인

    public Item(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return createdDate == null;
    }
}
