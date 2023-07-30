package bd.gov.lims.common.service;

import bd.gov.lims.common.dto.BaseDto;
import bd.gov.lims.common.support.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public abstract class ApiResponseService {

    public static <S extends BaseDto> ResponseEntity<Mono<ApiResponseDto<S>>> generateResponse(S data, HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .body(
                        Mono.just(ApiResponseDto.<S>builder()
                                .nonce(Instant.now().toEpochMilli())
                                .status(status.value())
                                .message(message)
                                .payload(data)
                                .build())
                );
    }

    public static <S> ResponseEntity<Mono<ApiResponseDto<S>>> generateResponse(Optional<S> data, HttpStatus status, String message) {
        if (data.isPresent()) {
            return ResponseEntity
                    .status(status)
                    .body(
                            Mono.just(ApiResponseDto.<S>builder()
                                    .nonce(Instant.now().toEpochMilli())
                                    .status(status.value())
                                    .message(message)
                                    .payload(data.get())
                                    .build())
                    );
        } else {
            ErrorDto errorDto = ErrorDto.builder()
                    .code("NOT_FOUND")
                    .message("Entity is not found")
                    .build();
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND.value())
                    .body(
                            Mono.just(ApiResponseDto.<S>builder()
                                    .nonce(Instant.now().toEpochMilli())
                                    .status(HttpStatus.NOT_FOUND.value())
                                    .message("Entity is not found")
                                    .error(errorDto)
                                    .build())
                    );
        }
    }

    public static <S extends BaseDto> ResponseEntity<Mono<ListResponseDto<S>>> generateResponse(List<S> data, HttpStatus status, String message) {
        int total = data == null ? 0 : data.size();
        return ResponseEntity
                .status(status)
                .body(
                        Mono.just(ListResponseDto.<S>builder()
                                .nonce(Instant.now().toEpochMilli())
                                .status(status.value())
                                .message(message)
                                .total(total)
                                .payload(data)
                                .build()
                        )
                );

    }

    public static <S extends BaseDto> ResponseEntity<Mono<PaginationResponseDto<S>>> generateResponse(Page<S> page, HttpStatus status, String message) {
        return
                ResponseEntity
                        .status(status)
                        .body(
                                Mono.just(PaginationResponseDto.<S>builder()
                                        .nonce(Instant.now().toEpochMilli())
                                        .status(status.value())
                                        .message(message)
                                        .payload(page.getContent())
                                        .pageable(PageDto.builder()
                                                .pageNumber(page.getNumber()+1)
                                                .pageSize(page.getSize())
                                                .totalElements(page.getTotalElements())
                                                .totalPages(page.getTotalPages())
                                                .first(page.isFirst())
                                                .last(page.isLast())
                                                .build())
                                        .build()
                                )
                        );

    }

    public static ResponseEntity<Mono<DeleteResponseDto>> generateResponse(HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .body(
                        Mono.just(DeleteResponseDto.builder()
                                .nonce(Instant.now().toEpochMilli())
                                .status(status.value())
                                .message(message)
                                .deleted(true)
                                .build()
                        )
                );
    }

}
