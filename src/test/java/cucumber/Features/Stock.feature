Feature: Inventory Adjustment

  Background:
    Given the user is logged in and redirected to the dashboard page

  Scenario: Penyesuaian Stok Produk
    When the user clicks Barang menu in the sidebar
    And  the user click Persediaan menu
    And the user selects a product
    And then the user clicks the Penyesuaian Persediaan button on the top right side
    And user double click on the input field
#    And the user add stock
    And the user click simpan button
    Then the user successfully adds stock