create table if not exists stock
(
    id             bigint auto_increment,
    name           varchar(255),
    symbol         varchar(255),
    stock_exchange enum ('AMEX','KOSDAQ','KOSPI','NASDAQ','NYSE'),
    listing_date   date,
    primary key (id)
);
create table if not exists stock_price
(
    id       bigint auto_increment,
    stock_id bigint,
    date     date,
    price    decimal(38, 2),
    primary key (id),
    constraint stock_price_stock_id_foreign_key foreign key (stock_id) references stock
);
create table if not exists dividend
(
    id       bigint auto_increment,
    stock_id bigint,
    date     date,
    amount   decimal(38, 2),
    primary key (id),
    constraint dividend_stock_id_foreign_key foreign key (stock_id) references stock
);
create table if not exists income_statement
(
    id               bigint auto_increment,
    stock_id         bigint,
    date             date,
    operating_income bigint,
    revenue          bigint,
    net_income       bigint,
    primary key (id),
    constraint income_statement_stock_id_foreign_key foreign key (stock_id) references stock
);
create table if not exists market_capitalization
(
    id           bigint auto_increment,
    stock_id     bigint,
    date         date,
    market_value bigint,
    primary key (id),
    constraint market_capitalization_stock_id_foreign_key foreign key (stock_id) references stock
);
create table if not exists member
(
    id   bigint auto_increment,
    name varchar(255),
    primary key (id)
);
