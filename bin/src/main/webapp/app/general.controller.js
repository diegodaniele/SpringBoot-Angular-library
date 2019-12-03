angular.module("crudApp").controller("GeneralController", GeneralController);

GeneralController.inject = [ '$scope', 'Book', 'Category' ];


function GeneralController($scope, Book, Category) {
	$scope.categories = Category.query();
	$scope.books = Book.query();

	$scope.book = {};
	$scope.category = {};

	$scope.saveBook = function() {
		if ($scope.book.id !== undefined) {
			Book.update($scope.book, function() {
				$scope.books = Book.query();
				$scope.book = {};
			});
		} else {
			Book.save($scope.book, function() {
				$scope.books = Book.query();
				$scope.book = {};
			});
		}
	}

	$scope.updateBookInit = function(book) {
		$scope.book = book;
	}

	$scope.deleteBook = function(book) {
		book.$delete({id: book.id}, function() {
			$scope.books = Book.query();
		});
	}

	$scope.saveCategory = function() {
		if ($scope.category.id !== undefined) {
			Category.update($scope.category, function() {
				$scope.categories = Category.query();
				$scope.category = {};
			});
		} else {
			Category.save($scope.category, function() {
				$scope.categories = Category.query();
				$scope.category = {};
			});
		}
	}

	$scope.deleteCategory = function(category) {
		category.$delete({id: category.id}, function() {
			$scope.categories = Category.query();
		});
	}

	$scope.updateCategoryInit = function(category) {
		$scope.category = category;
	}
	
	
	
}