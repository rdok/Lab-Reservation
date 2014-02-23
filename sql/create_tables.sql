SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `collegedb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `collegedb` ;

-- -----------------------------------------------------
-- Table `collegedb`.`courses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegedb`.`courses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `instructor_id` INT NULL,
  `student_limit` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `collegedb`.`students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegedb`.`students` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `app_student_id` INT NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `advisor_id` INT NULL,
  `phone` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `comments` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `collegedb`.`instructors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegedb`.`instructors` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `app_instructor_id` INT NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `phone` INT NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `collegedb`.`classes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegedb`.`classes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `level` VARCHAR(45) NOT NULL,
  `courses_id` INT NOT NULL,
  `instructors_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_classes_courses1_idx` (`courses_id` ASC),
  INDEX `fk_classes_instructors1_idx` (`instructors_id` ASC),
  CONSTRAINT `fk_classes_courses1`
    FOREIGN KEY (`courses_id`)
    REFERENCES `collegedb`.`courses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_classes_instructors1`
    FOREIGN KEY (`instructors_id`)
    REFERENCES `collegedb`.`instructors` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `collegedb`.`instructors_courses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegedb`.`instructors_courses` (
  `instructors_id` INT NOT NULL,
  `courses_id` INT NOT NULL,
  PRIMARY KEY (`instructors_id`, `courses_id`),
  INDEX `fk_instructors_courses_courses1_idx` (`courses_id` ASC),
  CONSTRAINT `fk_instructors_courses_instructors1`
    FOREIGN KEY (`instructors_id`)
    REFERENCES `collegedb`.`instructors` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_instructors_courses_courses1`
    FOREIGN KEY (`courses_id`)
    REFERENCES `collegedb`.`courses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `collegedb`.`students_courses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegedb`.`students_courses` (
  `students_id` INT NOT NULL,
  `courses_id` INT NOT NULL,
  PRIMARY KEY (`students_id`, `courses_id`),
  INDEX `fk_students_courses_courses1_idx` (`courses_id` ASC),
  CONSTRAINT `fk_students_classes_students1`
    FOREIGN KEY (`students_id`)
    REFERENCES `collegedb`.`students` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_students_courses_courses1`
    FOREIGN KEY (`courses_id`)
    REFERENCES `collegedb`.`courses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `collegedb`.`date`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegedb`.`date` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `time_start` TIME NOT NULL,
  `time_end` TIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `collegedb`.`students_advisor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegedb`.`students_advisor` (
  `instructors_id` INT NOT NULL,
  `students_id` INT NOT NULL,
  PRIMARY KEY (`instructors_id`, `students_id`),
  INDEX `fk_students_advisor_students1_idx` (`students_id` ASC),
  CONSTRAINT `fk_students_advisor_instructors1`
    FOREIGN KEY (`instructors_id`)
    REFERENCES `collegedb`.`instructors` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_students_advisor_students1`
    FOREIGN KEY (`students_id`)
    REFERENCES `collegedb`.`students` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `collegedb`.`date_class`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegedb`.`date_class` (
  `date_id` INT NOT NULL,
  `classes_id` INT NOT NULL,
  PRIMARY KEY (`date_id`, `classes_id`),
  INDEX `fk_course_date_date1_idx` (`date_id` ASC),
  INDEX `fk_course_date_classes1_idx` (`classes_id` ASC),
  CONSTRAINT `fk_course_date_date1`
    FOREIGN KEY (`date_id`)
    REFERENCES `collegedb`.`date` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_course_date_classes1`
    FOREIGN KEY (`classes_id`)
    REFERENCES `collegedb`.`classes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `collegedb`.`students_classes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `collegedb`.`students_classes` (
  `students_id` INT NOT NULL,
  `classes_id` INT NOT NULL,
  PRIMARY KEY (`students_id`, `classes_id`),
  INDEX `fk_students_classes_classes1_idx` (`classes_id` ASC),
  CONSTRAINT `fk_students_classes_students2`
    FOREIGN KEY (`students_id`)
    REFERENCES `collegedb`.`students` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_students_classes_classes1`
    FOREIGN KEY (`classes_id`)
    REFERENCES `collegedb`.`classes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
