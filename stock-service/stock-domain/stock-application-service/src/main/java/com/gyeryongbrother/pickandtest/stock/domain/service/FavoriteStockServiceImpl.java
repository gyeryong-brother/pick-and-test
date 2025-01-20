package com.gyeryongbrother.pickandtest.stock.domain.service;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.CreateFavoriteStockCommand;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.CreateFavoriteStockResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.DeleteFavoriteStockCommand;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.FavoriteStockService;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.FavoriteStockQueryRepository;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.FavoriteStockRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteStockServiceImpl implements FavoriteStockService {

    private final FavoriteStockRepository favoriteStockRepository;
    private final FavoriteStockQueryRepository favoriteStockQueryRepository;

    @Override
    public CreateFavoriteStockResponse createFavoriteStock(CreateFavoriteStockCommand command) {
        validateAlreadyLiked(command);
        FavoriteStock favoriteStock = command.toDomain();
        FavoriteStock savedFavoriteStock = favoriteStockRepository.save(favoriteStock);
        return CreateFavoriteStockResponse.from(savedFavoriteStock);
    }

    private void validateAlreadyLiked(CreateFavoriteStockCommand command) {
        Optional<FavoriteStock> favoriteStockOptional = favoriteStockQueryRepository.findByStockIdAndMemberId(
                command.stockId(),
                command.memberId()
        );
        if (favoriteStockOptional.isPresent()) {
            throw new IllegalStateException("stock is already liked");
        }
    }

    @Override
    public void deleteFavoriteStock(DeleteFavoriteStockCommand command) {
        Long id = command.favoriteStockId();
        FavoriteStock favoriteStock = favoriteStockQueryRepository.findById(id);
        favoriteStock.validateCanDeleteBy(command.memberId());
        favoriteStockRepository.delete(favoriteStock);
    }
}
