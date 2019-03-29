ALTER TABLE `order`
  ADD COLUMN `detail` varchar(1000) NULL DEFAULT '' AFTER `deliver_time`;