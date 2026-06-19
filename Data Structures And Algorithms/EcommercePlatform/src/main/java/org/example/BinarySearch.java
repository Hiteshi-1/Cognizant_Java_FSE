package org.example;

public class BinarySearch {

    public static Product search(
            Product[] products,
            int targetId) {

        int left = 0;
        int right = products.length - 1;

        while(left <= right) {

            int mid =
                    left + (right - left) / 2;

            if(products[mid].productId
                    == targetId) {

                return products[mid];
            }

            if(products[mid].productId
                    < targetId) {

                left = mid + 1;
            }
            else {

                right = mid - 1;
            }
        }

        return null;
    }
}
