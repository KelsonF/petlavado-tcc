# frozen_string_literal: true

class StoreTest < ActiveSupport::TestCase
  def setup
    @user = User.create!(email: "owner@example.com", password: "password")
    @store = Store.new(name: "Test Store", description: "A test store", owner: @user)
  end

  test "valid store" do
    assert @store.valid?
  end

  test "invalid without name" do
    @store.name = nil
    assert_not @store.valid?
  end

  test "invalid without description" do
    @store.description = nil
    assert_not @store.valid?
  end

  test "invalid without owner" do
    @store.owner = nil
    assert_not @store.valid?
  end
end
