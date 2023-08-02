DROP TABLE IF EXISTS `hedgehog` CASCADE;
CREATE TABLE `hedgehog` (
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `age` INTEGER CHECK(age>=1 AND age<=20),
    `colour` VARCHAR(10),
    `name` VARCHAR(255)
);
