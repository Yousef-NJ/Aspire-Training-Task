package com.training.session11.cartfullreqresp.adapter.repository.rds;

import com.training.session11.cartfullreqresp.adapter.repository.CartAggregateRepository;
import com.training.session11.cartfullreqresp.adapter.repository.rds.entity.CartAggregateEntity;
import com.training.session11.cartfullreqresp.adapter.repository.rds.entity.ItemEntity;
import com.training.session11.cartfullreqresp.aggregate.CartAggregate;
import com.training.session11.cartfullreqresp.aggregate.CartStatus;
import com.training.session11.cartfullreqresp.aggregate.LineItem;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CartAggregateRDSRepository implements CartAggregateRepository {

    private final CartAggregateJpaRepository jpaRepository;

    private final ModelMapper mapper;

    public CartAggregateRDSRepository(CartAggregateJpaRepository jpaRepository, ModelMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void insert(CartAggregate cartAggregate) {
        jpaRepository.save(mapToEntity(cartAggregate));
    }

    @Override
    public Optional<CartAggregate> findById(String cartId) {
        return jpaRepository.findById(cartId)
                .map(this::mapToAggregate);
    }

    private CartAggregate mapToAggregate(CartAggregateEntity entity) {
        List<LineItem> items = entity.getItems().stream().map(itemEntity -> mapToLineItem(itemEntity)).collect(Collectors.toList());
        return new CartAggregate(items,entity.getUserId(),entity.getCartId(),CartStatus.valueOf(entity.getCartStatus()));
    }

    private CartAggregateEntity mapToEntity(CartAggregate cartAggregate) {
        Type listType = new TypeToken<List<ItemEntity>>() {}.getType();
        List<ItemEntity> items = mapper.map(cartAggregate.getLineItems(), listType);
        return CartAggregateEntity
                .builder()
                .cartId(cartAggregate.getCartId())
                .userId(cartAggregate.getUserId())
                .cartStatus(cartAggregate.getCartStatus().name())
                .items(items)
                .build();
    }

    private LineItem mapToLineItem(ItemEntity itemEntity){
        return LineItem.builder().itemNo(itemEntity.getItemNo()).qty(itemEntity.getQty()).price(itemEntity.getPrice()).build();
    }
}
