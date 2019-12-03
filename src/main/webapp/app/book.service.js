angular.module('crudApp').factory('Book', Book);

Book.$inject = [ '$resource' ];

function Book($resource) {
	var resourceUrl = 'api/book/:id';

	return $resource(resourceUrl, {}, {
		'update' : {
			method : 'PUT'
		}
	});
}