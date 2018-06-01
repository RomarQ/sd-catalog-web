// Shows Category Modal that allows to Add/Update a category
function openCategoryEditor(data) {
    if ( data.status === 'success' )
        $('#categoryModal').modal();
}

// Closes Category Modal after deleting it
function closeCategoryEditor(data) {
    $('#categoryModal').modal("hide");
}

// Shows Product Modal that allows to Add/Update a product
function openProductEditor(data) {
    if ( data.status === 'success' )
        $('#productModal').modal();
}

// Closes Product Modal after deleting it
function closeProductEditor() {
    $('#productModal').modal("hide");
}

// Shows User Modal that allows to update user information
function openUserEditor(data) {
    if ( data.status === 'success' )
        $('#userModal').modal();
}

// Closes User Modal after deleting it
function closeUserEditor() {
    $('#userModal').modal("hide");
}

// Shows Seller Info Modal
function openSellerInfo(data) {
    if ( data.status === 'success' )
        $('#sellerInfoModal').modal();
}

// Closes User Seller Info
function closeSellerInfo() {
    $('#sellerInfoModal').modal("hide");
}