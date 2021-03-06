PGDMP     -                    u           distribuidora    9.5.10    9.5.10     t           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            u           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            v           1262    24685    distribuidora    DATABASE        CREATE DATABASE distribuidora WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'pt_BR.UTF-8' LC_CTYPE = 'pt_BR.UTF-8';
    DROP DATABASE distribuidora;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            w           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    6            x           0    0    public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    6                        3079    12397    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            y           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    24686    cliente    TABLE       CREATE TABLE cliente (
    cpf character varying(14) NOT NULL,
    nome character varying(100) NOT NULL,
    rua character varying(100) NOT NULL,
    nascimento date NOT NULL,
    cidade character varying(100) NOT NULL,
    telefone character varying(14)
);
    DROP TABLE public.cliente;
       public         postgres    false    6            �            1259    24689    funcionario    TABLE     �  CREATE TABLE funcionario (
    cpf character varying(11) NOT NULL,
    cod integer NOT NULL,
    nome character varying(100) NOT NULL,
    rua character varying(50) NOT NULL,
    funcao character varying(30) NOT NULL,
    bairro character varying NOT NULL,
    numero integer NOT NULL,
    nascimento date NOT NULL,
    senha character varying(50),
    telefone character varying(15)
);
    DROP TABLE public.funcionario;
       public         postgres    false    6            �            1259    24695    produto    TABLE     �   CREATE TABLE produto (
    codigo character varying(10) NOT NULL,
    nome character varying(50) NOT NULL,
    preco real NOT NULL,
    quantidade integer NOT NULL
);
    DROP TABLE public.produto;
       public         postgres    false    6            �            1259    24698    venda    TABLE     �   CREATE TABLE venda (
    data_venda date NOT NULL,
    codigo character varying(10) NOT NULL,
    cpf character varying(14) NOT NULL,
    venda_aprovada boolean,
    quantidade integer,
    preco real,
    id integer NOT NULL
);
    DROP TABLE public.venda;
       public         postgres    false    6            �            1259    24719    venda_id_seq    SEQUENCE     n   CREATE SEQUENCE venda_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.venda_id_seq;
       public       postgres    false    184    6            z           0    0    venda_id_seq    SEQUENCE OWNED BY     /   ALTER SEQUENCE venda_id_seq OWNED BY venda.id;
            public       postgres    false    185            �           2604    24721    id    DEFAULT     V   ALTER TABLE ONLY venda ALTER COLUMN id SET DEFAULT nextval('venda_id_seq'::regclass);
 7   ALTER TABLE public.venda ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    185    184            m          0    24686    cliente 
   TABLE DATA               H   COPY cliente (cpf, nome, rua, nascimento, cidade, telefone) FROM stdin;
    public       postgres    false    181          n          0    24689    funcionario 
   TABLE DATA               h   COPY funcionario (cpf, cod, nome, rua, funcao, bairro, numero, nascimento, senha, telefone) FROM stdin;
    public       postgres    false    182   x       o          0    24695    produto 
   TABLE DATA               ;   COPY produto (codigo, nome, preco, quantidade) FROM stdin;
    public       postgres    false    183          p          0    24698    venda 
   TABLE DATA               X   COPY venda (data_venda, codigo, cpf, venda_aprovada, quantidade, preco, id) FROM stdin;
    public       postgres    false    184   l       {           0    0    venda_id_seq    SEQUENCE SET     4   SELECT pg_catalog.setval('venda_id_seq', 23, true);
            public       postgres    false    185            �           2606    24702    cpf 
   CONSTRAINT     C   ALTER TABLE ONLY cliente
    ADD CONSTRAINT cpf PRIMARY KEY (cpf);
 5   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cpf;
       public         postgres    false    181    181            �           2606    24704    funcionario_pk 
   CONSTRAINT     W   ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_pk PRIMARY KEY (cpf, cod);
 D   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT funcionario_pk;
       public         postgres    false    182    182    182            �           2606    24706 
   produto_pk 
   CONSTRAINT     M   ALTER TABLE ONLY produto
    ADD CONSTRAINT produto_pk PRIMARY KEY (codigo);
 <   ALTER TABLE ONLY public.produto DROP CONSTRAINT produto_pk;
       public         postgres    false    183    183            �           2606    24709    cliente_venda_fk    FK CONSTRAINT     f   ALTER TABLE ONLY venda
    ADD CONSTRAINT cliente_venda_fk FOREIGN KEY (cpf) REFERENCES cliente(cpf);
 @   ALTER TABLE ONLY public.venda DROP CONSTRAINT cliente_venda_fk;
       public       postgres    false    181    2036    184            �           2606    24714    produto_venda_fk    FK CONSTRAINT     l   ALTER TABLE ONLY venda
    ADD CONSTRAINT produto_venda_fk FOREIGN KEY (codigo) REFERENCES produto(codigo);
 @   ALTER TABLE ONLY public.venda DROP CONSTRAINT produto_venda_fk;
       public       postgres    false    183    184    2040            m   Q  x���=o�0�g�W0���z�ԱR�.�(=z����u�.7Uj����JG�Z6ךZx������5f_㰅)�]v��ځm[�e�-�wܖ�uS� 9UE�\��֤�EC\�+]W��:��?�+2�k�C�i�"�[���E�z7��|BB�&��R"�L.�v����l��~i��ą ,(�0��[C�M���Dkm�.)�9S!�$?��ɘ# ^Z����S���wU:D���:\Ҁ�~�-}&VRnR���D[�eLR�褍�(���/���Ğ�����I_�	�r���N�5H�`K�!�D?��_�aY�\�\iiS��@�P����f�R?\��J      n   |   x�34�NCN�Ҝ�|Ί
��ԢԼ�T�Ѐ����X���8�@*��9MLA@Dp!1��TT�6�,5/%5%�a�Ԙ$�<��q�LM��������Y��0�jP
H�A1z\\\ �4�      o   X   x�5�;� E��e1��ۋ��3���<�L���8�[�)Hd�+$���6�Pܙ�0w��|�t�F�������*?��}��4*+      p   �   x���A� ��/Xx�$�/=��_5�B[��!Y#{X@�+rQ*�,b|ؙ�R�#�+a��I��9S�W�:�jvt��gH��XM9��v���D�e����e�NQ��`�>N0S�+�n)���ꪲ>_Xu�w�������脟�`��d�%�~c�����������s�     