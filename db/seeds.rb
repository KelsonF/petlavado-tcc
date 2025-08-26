# This file should ensure the existence of records required to run the application in every environment (production,
# development, test). The code here should be idempotent so that it can be executed at any point in every environment.
# The data can then be loaded with the bin/rails db:seed command (or created alongside the database with db:setup).
#
# Example:
#
Product.create!([
  {
    name: 'Product 1',
    description: 'Description for Product 1',
    price: 10.99,
    store_id: 1
  },
  {
    name: 'Product 2',
    description: 'Description for Product 2',
    price: 15.49,
    store_id: 1
  },
  {
    name: 'Product 3',
    description: 'Description for Product 3',
    price: 20.00,
    store_id: 1
  }
])
