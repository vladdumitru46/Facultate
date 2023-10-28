#include <iostream>
#include <string>

struct Node {
    std::string key;
    int value;
    Node* left;
    Node* right;

    Node(const std::string& k, int v) : key(k), value(v), left(nullptr), right(nullptr) {}
};

class LexicographicTable {
private:
    Node* root;

    Node* insert(Node* node, const std::string& key, int value) {
        if (node == nullptr) {
            return new Node(key, value);
        }

        if (key < node->key) {
            node->left = insert(node->left, key, value);
        }
        else if (key > node->key) {
            node->right = insert(node->right, key, value);
        }

        return node;
    }

    Node* search(Node* node, const std::string& key) {
        if (node == nullptr || node->key == key) {
            return node;
        }

        if (key < node->key) {
            return search(node->left, key);
        }

        return search(node->right, key);
    }

public:
    LexicographicTable() : root(nullptr) {}

    void insert(const std::string& key, int value) {
        root = insert(root, key, value);
    }

    int search(const std::string& key) {
        Node* result = search(root, key);
        if (result != nullptr) {
            return result->value;
        }
        return 0;
    }

    void display() {
        inorderTraversal(root);
        std::cout << std::endl;
    }
    void inorderTraversal(Node* node) {
        if (node == nullptr) {
            return;
        }

        inorderTraversal(node->left);
        std::cout << node->key << " " << node->value << std::endl;
        inorderTraversal(node->right);
    }
};