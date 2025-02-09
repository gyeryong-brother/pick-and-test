package com.gyeryongbrother.pickandtest.stock.domain.service;

import static com.gyeryongbrother.pickandtest.stock.domain.service.exception.StockServiceExceptionType.CAN_NOT_DELETE_FAVORITE_STOCK;
import static com.gyeryongbrother.pickandtest.stock.domain.service.exception.StockServiceExceptionType.STOCK_IS_ALREADY_LIKED;

import com.gyeryongbrother.pickandtest.stock.domain.core.entity.FavoriteStock;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.CreateFavoriteStockCommand;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.CreateFavoriteStockResponse;
import com.gyeryongbrother.pickandtest.stock.domain.service.dto.DeleteFavoriteStockCommand;
import com.gyeryongbrother.pickandtest.stock.domain.service.exception.StockServiceException;
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
            throw new StockServiceException(STOCK_IS_ALREADY_LIKED);
        }
    }

    @Override
    public void deleteFavoriteStock(DeleteFavoriteStockCommand command) {
        Long id = command.favoriteStockId();
        FavoriteStock favoriteStock = favoriteStockQueryRepository.getById(id);
        if (favoriteStock.canDeleteBy(command.memberId())) {
            favoriteStockRepository.delete(favoriteStock);
            return;
        }
        throw new StockServiceException(CAN_NOT_DELETE_FAVORITE_STOCK);
    }
}
