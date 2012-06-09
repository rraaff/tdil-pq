ALTER TABLE IMAGE_GALLERY ADD (`imageExt` VARCHAR(10) NULL);
ALTER TABLE IMAGE_GALLERY ADD (`image_id` INT NULL);

UPDATE IMAGE_GALLERY ig 
SET imageExt = (SELECT iig.imageExt FROM IMAGE_IN_GAL iig WHERE iig.id_gallery = ig.id AND iig.orderNumber = 0),
image_id = (SELECT iig.image_id FROM IMAGE_IN_GAL iig WHERE iig.id_gallery = ig.id AND iig.orderNumber = 0);

COMMIT;